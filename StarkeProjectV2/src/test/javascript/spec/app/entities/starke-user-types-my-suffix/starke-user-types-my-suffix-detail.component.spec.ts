/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { Starkev2TestModule } from '../../../test.module';
import { StarkeUserTypesMySuffixDetailComponent } from 'app/entities/starke-user-types-my-suffix/starke-user-types-my-suffix-detail.component';
import { StarkeUserTypesMySuffix } from 'app/shared/model/starke-user-types-my-suffix.model';

describe('Component Tests', () => {
    describe('StarkeUserTypesMySuffix Management Detail Component', () => {
        let comp: StarkeUserTypesMySuffixDetailComponent;
        let fixture: ComponentFixture<StarkeUserTypesMySuffixDetailComponent>;
        const route = ({ data: of({ starkeUserTypes: new StarkeUserTypesMySuffix(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Starkev2TestModule],
                declarations: [StarkeUserTypesMySuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(StarkeUserTypesMySuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(StarkeUserTypesMySuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.starkeUserTypes).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
