/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { Starkev2TestModule } from '../../../test.module';
import { StarkeUserTypesMySuffixComponent } from 'app/entities/starke-user-types-my-suffix/starke-user-types-my-suffix.component';
import { StarkeUserTypesMySuffixService } from 'app/entities/starke-user-types-my-suffix/starke-user-types-my-suffix.service';
import { StarkeUserTypesMySuffix } from 'app/shared/model/starke-user-types-my-suffix.model';

describe('Component Tests', () => {
    describe('StarkeUserTypesMySuffix Management Component', () => {
        let comp: StarkeUserTypesMySuffixComponent;
        let fixture: ComponentFixture<StarkeUserTypesMySuffixComponent>;
        let service: StarkeUserTypesMySuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Starkev2TestModule],
                declarations: [StarkeUserTypesMySuffixComponent],
                providers: []
            })
                .overrideTemplate(StarkeUserTypesMySuffixComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(StarkeUserTypesMySuffixComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(StarkeUserTypesMySuffixService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new StarkeUserTypesMySuffix(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.starkeUserTypes[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
