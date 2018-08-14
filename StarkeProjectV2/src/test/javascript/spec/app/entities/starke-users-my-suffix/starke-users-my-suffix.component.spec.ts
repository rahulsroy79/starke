/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { Starkev2TestModule } from '../../../test.module';
import { StarkeUsersMySuffixComponent } from 'app/entities/starke-users-my-suffix/starke-users-my-suffix.component';
import { StarkeUsersMySuffixService } from 'app/entities/starke-users-my-suffix/starke-users-my-suffix.service';
import { StarkeUsersMySuffix } from 'app/shared/model/starke-users-my-suffix.model';

describe('Component Tests', () => {
    describe('StarkeUsersMySuffix Management Component', () => {
        let comp: StarkeUsersMySuffixComponent;
        let fixture: ComponentFixture<StarkeUsersMySuffixComponent>;
        let service: StarkeUsersMySuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Starkev2TestModule],
                declarations: [StarkeUsersMySuffixComponent],
                providers: []
            })
                .overrideTemplate(StarkeUsersMySuffixComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(StarkeUsersMySuffixComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(StarkeUsersMySuffixService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new StarkeUsersMySuffix(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.starkeUsers[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
